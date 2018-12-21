#!/bin/bash
sourcePath="/Users/finup/myTest/shell/gitpull.sh"
targetPath="/Users/finup/myTest/shell/cptest/target.sh"
haveSource=`find ${sourcePath}`
haveTarget=`find ${targetPath}`
echo "haveSource: ${haveSource}"
# -n -z 都是对字符串操作 变量一定要加引号 这样才能是字符串
# if[] 里使用变量依旧可以使用${}
if [ -n "${haveTarget}" ]; then
    echo "targetFile have same name"
    exit
fi

if [ -n "$haveSource" ]; then
    echo "path is right haveSource:${haveSource}"
    cp ${sourcePath} ${targetPath}
else
    echo "can't find targetPath:${targetPath}"
fi