package com.mantas;

/**
 * 基于spring的生命周期, 在适当的阶段设置系统的配置等信息
 * 包括:
 *  1. 容器初始化 配置
 *  2. 系统默认值 配置
 *  3. 系统退出重置
 *  4. 容器销毁重置
 */
public class Bootstrap {

    void initBean() {};

    void destroyBean() {};
}
