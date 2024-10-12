package Service.impl;

import Service.ViewInterface;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.springframework.stereotype.Service;
@Service
public class ViewService implements ViewInterface {
    @Override
    public void welcomeInterface(){
        System.out.println("欢迎使用学生成绩管理系统！！！");
        String art =
                        " W   W  EEEEE  L      CCCC  OOO  M   M  EEEEE \n" +
                        " W W W  E      L     C     O   O MM MM  E     \n" +
                        " W W W  EEEEE  L     C     O   O M M M  EEEEE \n" +
                        " W W W  E      L     C     O   O M   M  E     \n" +
                        " W   W  EEEEE  LLLLL  CCCC  OOO  M   M  EEEEE \n";

        System.out.println(art);
        //列出功能选项
        System.out.println("菜单命令如下：");
        System.out.println();
    }
}
