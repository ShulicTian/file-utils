package utils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

/**
 * @Desc 窗体设置工具
 * @Date 2019.05.12
 * @author ShulicTian
 */
public class WindowUtils {

    public static Toolkit kit = Toolkit.getDefaultToolkit();

    public static void setLocationPoint(Frame frame) {
        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();

        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }

    /**
     * @Desc 创建文件选择器
     * @param fileSelectionMode
     * @return
     */
    public static JFileChooser folderSelector(int fileSelectionMode) {

        JFileChooser fileChooser = new JFileChooser();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
        fileChooser.setDialogTitle("请选择要上传的文件...");
        fileChooser.setApproveButtonText("确定");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(fileSelectionMode);

        return fileChooser;
    }
}
