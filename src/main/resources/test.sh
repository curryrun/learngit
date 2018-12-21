#! /bin/bash
path1="/newhd01/ulog_winup/"
path2="/newhd01/ulog_winup/real_for_flume/"
timelimit1="+6"
timelimit2="-17"
for i in "Search" "Download" "Browse"; do
        list_newfiles=`cd $path1$i && find -iname "*.txt" -type f -mmin $timelimit2 -a -mmin $timelimit1 | awk '{print substr($1,3)}'`
        echo ""
        echo "target folder: $path2${i}1"
        echo "Trying to copy those files in $path1$i"
        echo "$list_newfiles"
        echo ""
        OLD_IFS=$IFS
        IFS=$'\n'
        arr_newfiles=($list_newfiles)
        for s in ${arr_newfiles[@]}; do
                #echo "$s"
                isfindthisfile=`find $path2${i}1 -iname $s`
                if [ -z "$isfindthisfile" ]; then
                        echo "$path1$i/$s is not in target folder,try to copy!"
                        cp "$path1$i/$s" "$path2${i}1/$s.TMP"
                        mv "$path2${i}1/$s.TMP" "$path2${i}1/$s"
                        echo "$path1$i/$s has been moved successfully!!!"
                else
                        echo "$path1$i/$s is allready in target folder,trying to copy next !"
                fi
        done
done
IFS=$OLD_IFS