## 场景模拟练习


### 1 请求日志输出
情景：
* 我想通过控制台，看到每个请求的访问情况，判断请求是否进入到应用中

要求：
* 每次请求过来，控制台可以输出：请求路径、请求参数
* 请求处理结束后，控制台可以输出：请求路径、请求参数、耗时


### 2 请求日志跟踪
情景：
* 控制台打印的日志繁杂
* 当出现异常时，我无法定位这个请求的起源信息，找不到这个异常是属于哪个请求的

要求：
* 日志打印有关联性


### 3 屏蔽无用的请求日志输出
情景：
* 假设某个接口，只是提供了简单的查询返回，但是请求频次非常高
* 控制台频繁打印该接口的日志，影响我查看重要的日志

要求：
* 在控制台上面，看不到该接口的请求日志输出