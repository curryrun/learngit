#!/bin/bash
pathArr=("/Users/finup/Documents/qianzhan-core-aggregator" "/Users/finup/Documents/puhui-newapp-all" "/Users/finup/Documents/puhui-newchannel-all");
gitPull(){
    pathItem=$1
    echo "function path is ${pathItem}"
    cd $pathItem
    /usr/bin/git checkout master
    /usr/bin/git pull
#    /usr/bin/git checkout test
#    /usr/bin/git pull
}
for path in ${pathArr[@]}
do
    echo "project path is ${path}"
    gitPull $path
done