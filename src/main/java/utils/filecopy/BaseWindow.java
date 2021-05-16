package utils.filecopy;

/**
 * @Desc 弹出框通用接口
 * @Date 2019.05.13
 * @author ShulicTian
 */
public interface BaseWindow {

    /**
     * 初始化内部组件
     */
    void init();

    /**
     * 注册事件
     */
    void registerAction();

    /**
     * 设置弹框位置
     */
    void setLocationPoint();

    /**
     * 组装内部组件
     */
    void assemble();

    /**
     * 加载外部组件(菜单组件)
     */
    void loadComponents();

}
