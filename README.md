# DBeaver Agent

针对老版本的破解研究在 [老版本](/README-63-70.md)

新版本在获取密钥上没什么变化,老版本 agent 仍然可用

但是许可生成上有变化,这里我们讨论 Ultimate 版  
产品 ID 叫做 `dbeaver-ue`  
通过反射操纵 `LMLicense.yearsNumber` 可以实现修改支持年数,最大为 127  
如果不使用到期日期,那么就等于永久许可

剩下的内容看看单元测试 `UltimateLicense` 就知道了

## 依赖

由于单元测试使用了来自 DBeaver 的代码,所以你需要准备好 DBeaver 的一些包  
把下列的包放入到 libs 文件夹

- `com.dbeaver.ee.runtime` 基础运行时,获取密钥等信息在里面
- `com.dbeaver.lm.core` 许可核心
- `org.jkiss.lm` 还是许可核心
- `org.jkiss.utils` 提供一些组件供许可生成
