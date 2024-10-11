package Service.impl;

import Service.ViewInterface;

public class ViewService implements ViewInterface {
    public void welcomeInterface(){
        System.out.println("欢迎使用学生成绩管理系统！！！");
        String art =
                "  _____ _                 _           _____ _           _ \n" +
                        " / ____| |               | |         / ____| |         | |\n" +
                        "| (___ | |__   __ _ _ __ | | _____  | (___ | |__   __ _| |\n" +
                        " \\___ \\| '_ \\ / _` | '_ \\| |/ / _ \\  \\___ \\| '_ \\ / _` | |\n" +
                        " ____) | | | | (_| | | | |   <  __/  ____) | | | | (_| | |\n" +
                        "|_____/|_| |_|\\__,_|_| |_|_|\\_\\___| |_____/|_| |_|\\__,_|_|\n";
        System.out.println(art);
        //列出功能选项

    }
}
