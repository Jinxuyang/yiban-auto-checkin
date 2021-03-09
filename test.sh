#!/bin/sh

APP_NAME=yiban-auto-checkin
PROJECT_PATH=/root/yiban-auto-checkin/yiban-auto-checkin
PROJECT_JAR_PATH=${PROJECT_PATH}/target

# 检测代码是否有更新

LOG=$(git pull)
if [ "${LOG}" = "Already up-to-date." ]; then
  echo "当前版本为最新版本"
  exit 1
else
  echo "更新到最新版本，开始编译"
  # 开始打包程序
  mvn clean package -Dmaven.test.skip=true
  if [ $? != 0 ]
  then
    echo "打包时出现错误"
    exit 1
  fi
fi

# 寻找jar包
for file in $(ls ${PROJECT_JAR_PATH})
do
  if [ "${file##*.}" = "jar" ]; then
      JAR_NAME=${file}
      echo "找到jar包:${JAR_NAME}"
      break
  fi
done

if [ -z ${JAR_NAME} ];then
  echo "未找到jar包"
  exit 1
fi

PID=$(ps aux | grep ${APP_NAME} | grep -v grep | awk '{printf $2}')

if [ -n ${PID} ]; then
  kill -9 ${PID}
  echo "停止旧程序(pid:${PID})"
else
  echo "程序并未运行"
fi

echo "开始启动新程序"
nohup java -jar ${PROJECT_JAR_PATH}/${JAR_NAME} > ${JAR_NAME%.*}.log &
PID=$(ps aux | grep ${APP_NAME} | grep -v grep | awk '{printf $2}')
echo "启动新程序(pid:${PID})"
