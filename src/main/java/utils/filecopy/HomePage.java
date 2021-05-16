package utils.filecopy;

import utils.WindowUtils;
import utils.imagepr.ImageprWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ShulicTian
 * @Desc 主页
 * @Date 2019.05.12
 */
public class HomePage extends JFrame implements BaseWindow {

    private JPanel jPanel = null;
    private FlowLayout flowLayout = null;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JButton jButton3 = null;
    private JButton jButton4 = null;

    public HomePage() {
        init();
        assemble();
        this.setTitle("主页");
        this.setSize(150, 210);
        setLocationPoint();
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(jPanel);
        this.setVisible(true);
    }

    @Override
    public void init() {

        jButton1 = new JButton("文件复制");
        jButton2 = new JButton("敬请期待");
        jButton4 = new JButton("图片水印");
        jButton3 = new JButton("退出");
        flowLayout = new FlowLayout(FlowLayout.CENTER, 15, 15);
        jPanel = new JPanel(flowLayout);
        registerAction();
        loadComponents();
    }

    @Override
    public void registerAction() {

        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        CopyFolderByKeyword.getCopyFolderByKeyword();
                    }
                });
            }
        };

        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "新功能未上线，敬请期待");
            }
        };

        ActionListener actionListener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };

        ActionListener actionListener4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ImageprWindow.getImageprWindow();
                    }
                });
            }
        };

        jButton1.addActionListener(actionListener1);
        jButton2.addActionListener(actionListener2);
        jButton3.addActionListener(actionListener3);
        jButton4.addActionListener(actionListener4);
    }

    @Override
    public void setLocationPoint() {
        WindowUtils.setLocationPoint(this);
    }

    @Override
    public void assemble() {
        jPanel.add(jButton1);
        jPanel.add(jButton4);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
    }

    @Override
    public void loadComponents() {
        CopyFolderByKeyword.getCopyFolderByKeyword();
        ImageprWindow.getImageprWindow();
    }

}
