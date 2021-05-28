# Demo

## 测量启动时间
```shell script
adb shell am start -W com.lingo.github/.MainActivity
```

ThisTime: 最后一个Activity启动耗时
TotalTime: 所有Activity启动耗时
WaitTime: AMS启动Activity的总耗时

## systrace

```shell script
python ~/Library/Android/sdk/platform-tools/systrace/systrace.py -b 32768 -t 5 -a com.lingo.github -o performance.html sched gfx view wm am app
```