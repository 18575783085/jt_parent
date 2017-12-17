/**
 * Copyright (C), 2017-？, OYE有限公司
 * FileName: PicUploadController
 * Author:   OYE 517553812@qq.com
 * Date:     2017/12/17 12:21
 * Description: 图片上传控制器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间        版本号            描述
 */
package top.ou.jt.manage.controller;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.ou.jt.common.service.PropertieService;
import top.ou.jt.common.vo.PicUploadResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图片上传控制器〉
 *
 * @author OYE 517553812@qq.com
 * @create 2017/12/1712:21
 * @since 1.0.0
 */
@Controller
public class PicUploadController {

    private static final Logger log = Logger.getLogger(PicUploadController.class);

    @Autowired
    private PropertieService propertieService;

    /**
     * 文件上传的步骤：
     * 1、拿到这个文件名，拿到扩展名，判断是否合法的图片文件后缀
     *    .jpg、.png、.gif、.jpeg
     * 2、判断是否为木马
     * 3、生成两个路径：图片的真是存放路径，网络访问的相对路径
     * 4、图片存放目录，一个文件太多，所以用时间加3位随机数来命名图片名字：yyyy/MM/dd
     * 5、不能使用原文件名称，重命名，计算方法（不唯一）
     *  【 currentTime+3位随机数/uuid+3位随机 】
     * 6、保存图片
     * 7、生成一个picUpload的对象来封装数据
     *
     * @param uploadFile 上传图片
     * @return
     */
    @RequestMapping("pic/upload")
    @ResponseBody
    public PicUploadResult picUpload(MultipartFile uploadFile){
        PicUploadResult uploadResult = new PicUploadResult();

        try {
            //1.获取上传的图片名称
            String oldFileName = uploadFile.getOriginalFilename();
            //2.获取图片的后缀名
            String suffixFileName = oldFileName.substring(oldFileName.lastIndexOf("."));

            //3.判断文件后缀名判断，正则，如果不是满足图片后缀
            if (!suffixFileName.matches("^.*(jpg|png|gif|jpeg)$")){
                //如果不是，设置错误状态码
                uploadResult.setError(1);
            }

            //匹配
            //4.创建图片写入流
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            //4.1将获取到图片的宽高，设置参数
            uploadResult.setHeight(bufferedImage.getHeight()+"");
            uploadResult.setWidth(bufferedImage.getWidth()+"");

            //5.生成有格式的路径（带时间生成文件夹）
            String picDir = "images/"+new SimpleDateFormat("yyyy/MM/dd").format(new Date())+"/";

            //5.1相对路径
            String urlPrefix = propertieService.IMAGE_BASE_URL+picDir;
            //5.2绝对路径：“c://jt-upload/images/”+picDir (属性注入的解耦操作)
            String path = propertieService.REPOSITORY_PATH+picDir;

            //6.创建文件夹和文件
            File dir = new File(path);

            //7.判断该文件夹是否存在
            if(!dir.exists()){//不存在路径，直接创建
                //7.1创建多级目录
                dir.mkdirs();
            }

            //8.生成根据计算方法完成的文件名称重命名
            String fileName = System.currentTimeMillis()+""+ RandomUtils.nextInt(100,999)+suffixFileName;

            //9.设置图片路径
            uploadResult.setUrl(urlPrefix+fileName);

            //10.保存到磁盘操作
            uploadFile.transferTo(new File(path+fileName));
        } catch (Exception e){
            //写错误日志
            log.error(e.getMessage());
            uploadResult.setError(1);
        }

        return uploadResult;


    }

}
