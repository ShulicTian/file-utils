package utils.imagepr;

import org.apache.commons.lang3.StringUtils;
import utils.filecopy.BaseWindow;
import utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ShulicTian
 * @Desc 图片添加水印
 * @Date 2019.05.14
 */
public class ImageprWindow extends JFrame implements BaseWindow {

    private JTextField input = null;
    private JTextField input2 = null;
    private JTextField input3 = null;
    private JTextField input4 = null;
    private JTextField input5 = null;
    private FlowLayout flowLayout = null;
    private JPanel jPanel = null;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JButton jButton3 = null;
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    private JLabel jLabel = null;
    private JLabel jLabel2 = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JFileChooser fileChooser = null;
    private JFileChooser fileChooser2 = null;
    private JFileChooser fileChooser3 = null;
    private static ImageprWindow imageprWindow = null;

    private ImageprWindow() {
        init();
        assemble();
        this.setTitle("添加水印");
        this.setSize(400, 250);
        setLocationPoint();
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(jPanel);
    }

    /**
     * @return
     * @Desc 采用懒汉式单例模式
     */
    public static ImageprWindow getImageprWindow() {

        if (imageprWindow == null) {
            synchronized (ImageprWindow.class) {
                if (imageprWindow == null) {
                    imageprWindow = new ImageprWindow();
                }
            }
        } else {
            imageprWindow.setVisible(true);
        }

        return imageprWindow;
    }

    @Override
    public void assemble() {

        jPanel.add(jLabel);
        jPanel.add(input);
        jPanel.add(jButton3);

        jPanel.add(jLabel2);
        jPanel.add(input2);
        jPanel.add(jButton4);

        jPanel.add(jLabel5);
        jPanel.add(input5);
        jPanel.add(jButton5);

        jPanel.add(jLabel3);
        jPanel.add(input3);

        jPanel.add(jLabel4);
        jPanel.add(input4);

        jPanel.add(jButton1);
        jPanel.add(jButton2);

    }

    @Override
    public void loadComponents() {

    }

    @Override
    public void init() {
        flowLayout = new FlowLayout(FlowLayout.RIGHT, 6, 10);
        initLabel();
        initInput();
        initButton();
        initJFileChooser();
        jPanel = new JPanel(flowLayout);
    }

    public void initJFileChooser() {
        fileChooser = WindowUtils.folderSelector(JFileChooser.FILES_ONLY);
        fileChooser2 = WindowUtils.folderSelector(JFileChooser.DIRECTORIES_ONLY);
        fileChooser3 = WindowUtils.folderSelector(JFileChooser.FILES_ONLY);
    }

    public void initLabel() {
        jLabel = new JLabel("请输入源路径：");
        jLabel2 = new JLabel("请输入目标路径：");
        jLabel3 = new JLabel("水印文字：");
        jLabel4 = new JLabel("旋转角度：");
        jLabel5 = new JLabel("水印图片：");
        jLabel.setHorizontalAlignment(JLabel.RIGHT);
        jLabel2.setHorizontalAlignment(JLabel.RIGHT);
        jLabel3.setHorizontalAlignment(JLabel.RIGHT);
        jLabel4.setHorizontalAlignment(JLabel.RIGHT);
        jLabel5.setHorizontalAlignment(JLabel.RIGHT);
        jLabel.setPreferredSize(new Dimension(110, 20));
        jLabel2.setPreferredSize(new Dimension(110, 20));
        jLabel3.setPreferredSize(new Dimension(110, 20));
        jLabel4.setPreferredSize(new Dimension(110, 20));
        jLabel5.setPreferredSize(new Dimension(110, 20));
    }

    public void initButton() {
        jButton1 = new JButton("确定");
        jButton2 = new JButton("取消");
        jButton3 = new JButton("选择");
        jButton4 = new JButton("选择");
        jButton5 = new JButton("选择");
        registerAction();
    }

    public void initInput() {
        input = new JTextField(15);
        input2 = new JTextField(15);
        input3 = new JTextField(21);
        input4 = new JTextField(21);
        input5 = new JTextField(15);
        input.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input3.setHorizontalAlignment(JLabel.LEFT);
        input4.setHorizontalAlignment(JLabel.LEFT);
    }

    @Override
    public void setLocationPoint() {
        WindowUtils.setLocationPoint(this);
    }

    @Override
    public void registerAction() {
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(isNull(input5.getText()));
                System.out.println(isNull(input3.getText()));
                System.out.println(isNull(input5.getText())&&isNull(input3.getText()));
                if (isNull(input.getText())) {
                    JOptionPane.showMessageDialog(null, "源路文件不能为空！");
                } else if (isNull(input2.getText())) {
                    JOptionPane.showMessageDialog(null, "目标文件夹不能为空！");
                } else if (isNull(input5.getText())&&isNull(input3.getText())) {
                    JOptionPane.showMessageDialog(null, "图片与文字水印必须填写其中一项！");
                } else {
                    List<String> result = addImagepr(input.getText(), input2.getText(), input5.getText(), input3.getText(), input4.getText());
                    if (result.size() > 0) {
                        String msg = "";
                        for (String str : result) {
                            msg += str + "\n";
                        }
                        JOptionPane.showMessageDialog(null, msg);
                    }else{
                        JOptionPane.showMessageDialog(null, "添加成功");
                        dispose();
                    }
                }
            }
        };

        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };

        ActionListener actionListener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == result) {
                    input.setText(fileChooser.getSelectedFile().getPath());
                }
            }
        };

        ActionListener actionListener4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser2.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == result) {
                    input2.setText(fileChooser2.getSelectedFile().getPath());
                }
            }
        };

        ActionListener actionListener5 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser3.showOpenDialog(null);
                if (JFileChooser.APPROVE_OPTION == result) {
                    input5.setText(fileChooser3.getSelectedFile().getPath());
                }
            }
        };

        jButton1.addActionListener(actionListener1);
        jButton2.addActionListener(actionListener2);
        jButton3.addActionListener(actionListener3);
        jButton4.addActionListener(actionListener4);
        jButton5.addActionListener(actionListener5);
    }

    public boolean isNull(String text){
        if(text != null && !"".equals(text)){
            return false;
        }
        return true;
    }

    public List<String> addImagepr(String sourceSrc, String targetSrc, String imgSrc, String text, String degree) {
        targetSrc = targetSrc + "\\imagepr.png";
        List<String> result = new ArrayList<>();
        if (isNull(degree)) {

            if (!isNull(imgSrc)) {
                if (!isNull(text)) {
                    result.add("文字与图片只能选一项");
                } else {
                    Imagepr.markImageByIcon(imgSrc, sourceSrc, targetSrc);
                }
            } else {
                Imagepr.markImageByText(text, sourceSrc, targetSrc);
            }

        } else {
            if (!isNull(imgSrc)) {
                if (!isNull(text)) {
                    result.add("文字与图片只能选一项");
                } else {
                    Imagepr.markImageByIcon(imgSrc, sourceSrc, targetSrc, Integer.parseInt(degree));
                }
            } else {
                Imagepr.markImageByText(text, sourceSrc, targetSrc, Integer.parseInt(degree));
            }
        }
        return result;
    }
}

