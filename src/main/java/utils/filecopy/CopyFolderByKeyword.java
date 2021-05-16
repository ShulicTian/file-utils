package utils.filecopy;

import org.apache.commons.lang3.StringUtils;
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
 * @Desc 文件复制页
 * @Date 2019.05.12
 */
public class CopyFolderByKeyword extends JFrame implements BaseWindow {

    private JTextField input = null;
    private JTextField input2 = null;
    private JTextField input3 = null;
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
    private JFileChooser fileChooser = null;
    private JFileChooser fileChooser2 = null;
    private JFileChooser fileChooser3 = null;
    private static CopyFolderByKeyword copyFolderByKeyword = null;


    private CopyFolderByKeyword() {
        init();
        assemble();
        this.setTitle("文件复制");
        this.setSize(400, 185);
        setLocationPoint();
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setContentPane(jPanel);
    }

    /**
     * @return
     * @Desc 采用懒汉式单例模式
     */
    public static CopyFolderByKeyword getCopyFolderByKeyword() {

        if (copyFolderByKeyword == null) {
            synchronized (CopyFolderByKeyword.class) {
                if (copyFolderByKeyword == null) {
                    copyFolderByKeyword = new CopyFolderByKeyword();
                }
            }
        } else {
            copyFolderByKeyword.setVisible(true);
        }

        return copyFolderByKeyword;
    }

    @Override
    public void assemble() {

        jPanel.add(jLabel3);
        jPanel.add(input3);
        jPanel.add(jButton5);
        jPanel.add(jLabel);
        jPanel.add(input);
        jPanel.add(jButton3);
        jPanel.add(jLabel2);
        jPanel.add(input2);
        jPanel.add(jButton4);
        jPanel.add(jButton1);
        jPanel.add(jButton2);

    }

    @Override
    public void loadComponents() {

    }

    @Override
    public void init() {
        flowLayout = new FlowLayout(FlowLayout.RIGHT, 10, 10);
        initLabel();
        initInput();
        initButton();
        initJFileChooser();
        jPanel = new JPanel(flowLayout);
    }

    public void initJFileChooser() {
        fileChooser = WindowUtils.folderSelector(JFileChooser.DIRECTORIES_ONLY);
        fileChooser2 = WindowUtils.folderSelector(JFileChooser.DIRECTORIES_ONLY);
        fileChooser3 = WindowUtils.folderSelector(JFileChooser.FILES_ONLY);
    }

    public void initLabel() {
        jLabel = new JLabel("请输入源路径：");
        jLabel2 = new JLabel("请输入目标路径：");
        jLabel3 = new JLabel("请输入关键字：");
        jLabel.setHorizontalAlignment(JLabel.RIGHT);
        jLabel2.setHorizontalAlignment(JLabel.RIGHT);
        jLabel3.setHorizontalAlignment(JLabel.RIGHT);
        jLabel.setPreferredSize(new Dimension(110, 20));
        jLabel2.setPreferredSize(new Dimension(110, 20));
        jLabel3.setPreferredSize(new Dimension(110, 20));
    }

    public void initButton() {
        jButton1 = new JButton("确定");
        jButton2 = new JButton("取消");
        jButton3 = new JButton("选择");
        jButton4 = new JButton("选择");
        jButton5 = new JButton("导入");
        registerAction();
    }

    public void initInput() {
        input = new JTextField(15);
        input2 = new JTextField(15);
        input3 = new JTextField(15);
        input.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        input3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
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
                if (input.getText() == null || "".equals(input.getText())) {
                    JOptionPane.showMessageDialog(null, "源路文件夹不能为空！");
                } else if (input2.getText() == null || "".equals(input2.getText())) {
                    JOptionPane.showMessageDialog(null, "目标文件夹不能为空！");
                } else {
                    String[] keys = input3.getText().split(",");
                    String errMsg = "";
                    String msgs = "";
                    msgs += "请确保关键词是以英文逗号进行分隔！\n\n";
                    msgs += "源目录：" + input.getText() + "\n";
                    msgs += "目标目录：" + input2.getText();

                    int result = JOptionPane.showConfirmDialog(null, msgs, "确认复制？", 0);
                    if (result == 0) {
                        List<String> errList = new ArrayList<>();
                        for (String key : keys) {
                            if (key.length() > 260) {
                                errMsg = "请输入正确关键词，如有多个请以英文逗号进行分隔";
                                break;
                            }
                            errList.addAll(excute(input.getText(), input2.getText(), key));
                        }

                        exportExceptionList(errList, input2.getText());

                        if (errList.size() > 0) {
                            errMsg = errList.size() + "个操作异常，请查看\"" + input2.getText() + "\"下的异常列表";
                        }

                        if (StringUtils.isNotEmpty(errMsg)) {
                            JOptionPane.showMessageDialog(null, errMsg, "异常列表", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "复制成功");
                            dispose();
                        }

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
                    String name = fileChooser3.getSelectedFile().getName();
                    if (name.endsWith(".txt")) {
                        input3.setText(getTxtContent(fileChooser3.getSelectedFile().getPath()));
                    } else {
                        JOptionPane.showMessageDialog(null, "只允许导入 txt 格式文件！");
                    }
                }
            }
        };

        jButton1.addActionListener(actionListener1);
        jButton2.addActionListener(actionListener2);
        jButton3.addActionListener(actionListener3);
        jButton4.addActionListener(actionListener4);
        jButton5.addActionListener(actionListener5);
    }

    /**
     * @param path
     * @return
     * @Desc 从文件中提取关键词
     */
    public String getTxtContent(String path) {

        File file = new File(path);
        BufferedReader  isr = null;
        StringBuffer sb = null;

        try {
            isr = new BufferedReader(new FileReader(file));
            sb = new StringBuffer();
            String line = null;
            while ((line = isr.readLine())  != null) {
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return StringUtils.deleteWhitespace(sb.toString());
    }

    /**
     * @param sourcePath 源目录
     * @param targetPath 目标目录
     * @param keyName    关键词
     * @return
     * @Desc 进行文件复制
     */
    public List<String> excute(String sourcePath, final String targetPath, final String keyName) {

        File folder = new File(sourcePath);

        final List<String> result = new ArrayList();

        if (folder.isFile()) {
            return null;
        }

        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (keyName != null && !"".equals(keyName)) {
                    if (file.getName().equals(keyName)) {
                        return true;
                    }
                    return false;
                }
                return true;
            }
        });

        if (subFolders != null && subFolders.length > 0) {
            for (final File f : subFolders) {
                if (f.isDirectory()) {
                    String path = targetPath + "\\" + f.getName();
                    result.addAll(iteratorFolder(f, path));
                } else {
                    try {
                        writerFile(f, targetPath);
                    } catch (IOException e) {
                        result.add(f.getPath());
                        e.printStackTrace();
                    }
                }
            }
        } else {
            result.add("未找到相关资源：" + keyName);
        }
        return result;
    }

    public void exportExceptionList(List<String> list, String targetPath) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(targetPath + "\\异常列表.txt"))));
            for (String msg : list) {
                bufferedWriter.append(msg+"\n");
            }
            bufferedWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param f
     * @param path
     * @return
     * @Desc 遍历所有子文件并复制
     */
    public List<String> iteratorFolder(File f, final String path) {

        final List<String> list = new ArrayList<>();
        if (f.list().length > 0) {
            f.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    if (file.isFile()) {
                        try {
                            writerFile(file, path);
                        } catch (IOException e) {
                            list.add("文件复制异常：" + path);
                            e.printStackTrace();
                        }
                        return false;
                    } else {
                        list.addAll(iteratorFolder(file, path + "\\" + file.getName()));
                    }
                    return false;
                }
            });
        }

        return list;
    }

    /**
     * @param file
     * @param targetPath
     * @throws IOException
     * @Desc 进行写入操作
     */
    public void writerFile(File file, String targetPath) throws IOException {

        InputStream is = null;
        OutputStream os = null;
        String targetFilePath = targetPath + "\\" + file.getName();
        File folder = new File(targetPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File targetFile = new File(targetFilePath);
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        is = new FileInputStream(file);
        os = new FileOutputStream(targetFile);
        byte[] b = new byte[1024];
        int len = 0;

        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }

        if (is != null) {
            is.close();
        }
        if (os != null) {
            os.close();
        }
    }
}

