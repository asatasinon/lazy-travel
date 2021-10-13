# 打包命令

```
mvn clean package appassembler:assemble
```

打包完之后，需要修改 `lazy-service-application` 文件,
在 `CLASSPATH=` 增加 `"$BASEDIR"/classes:` 

# 后台运行命令

```
cd lazy-service

nohup ./bin/lazy-service-application >./logs/start.log 2>&1 &
```
