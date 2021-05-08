# Retrofit

## 使用到的设计模式

### 动态代理模式
java 提供了动态生成代理类的方法: 
`Proxy.newProxyInstance(ClassLoader, [interface Class], InvocationHandler)`
代理`interface Class`接口，通过`InvocationHandler`回调

### 代理模式
retrofit封装了okHttp
retrofit2.call 代理了 okhttp3.call

### 适配器模式
通过CallAdapter实现到主线程的切换
适配RxJava

### 工厂模式
RequestFactory
CallFactory
CallAdapterFactory
ConvertFactory

### 建造者模式
Retrofit.Builder

## 激进验证
validateEagerly
在debug模式下使用激进验证，提早发现问题
在release模式下关闭激进验证，提高App启动性能