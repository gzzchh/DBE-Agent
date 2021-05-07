# DBeaver Agent

针对老版本的破解研究在 [老版本](/README-63-70.md)

新版本在获取密钥上没什么变化,老版本 agent 仍然可用

但是许可生成上有变化,这里我们讨论 Ultimate 版  
产品 ID 叫做 `dbeaver-ue`  
通过反射操纵 `LMLicense.yearsNumber` 可以实现修改支持年数,最大为 127

剩下的内容看看单元测试 `UltimateLicense` 就知道了
