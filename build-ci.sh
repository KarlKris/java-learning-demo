#!/bin/sh
# set -v
#####项目变量

baseDir=$(pwd)

copyPath=$baseDir/dist

echo
echo '====================>>删除dist目录...'

rm -rf $copyPath

echo '====================>>删除dist目录完成，3秒后开始打包'
echo

sleep 3s

distCharge=$copyPath/charge
mkdir -p $distCharge
distLogin=$copyPath/login
mkdir -p $distLogin
distGift=$copyPath/gift
mkdir -p $distGift
distManager=$copyPath/manager
mkdir -p $distManager
distServer=$copyPath/game
mkdir -p $distServer
distCenter=$copyPath/center
mkdir -p $distServer
distFight=$copyPath/battle
mkdir -p $distFight
distReport=$copyPath/report
mkdir -p $distReport
distMerge=$copyPath/merge
mkdir -p $distMerge

distVersion=$copyPath

sdkPath=$baseDir/sdk
commonPath=$baseDir/commons
chargePath=$baseDir/charge
loginPath=$baseDir/login
proxyPath=$baseDir/proxy
giftPath=$baseDir/gift
managerPath=$baseDir/manager

serverPath=$baseDir


#读取用户输入默认等待时间(s)
read_wait_time=2
echo "**************************版本数据***************************************"
echo common: `git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
version=$(git log --pretty=format:"%H" -1)
echo $version > $distVersion/record.txt
echo "***********************************************************************"

########打包sdk包
function package_current_sdk(){
	echo [INFO]--------打包sdk包开始-----------------
	pushd ${sdkPath}

	echo common: `git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
	mvn clean install -Dmaven.test.skip=true
	pk_result=$?
	popd
	if [ $pk_result -ne 0 ]
	then
		echo '------sdk包打失败(终止打包流程)--------'
		exit 1;
	fi
        echo [INFO]--------sdk包已打包完成---------------
	return 0;
}

########打包common包
function package_current_common(){
	echo [INFO]--------打包common包开始-----------------
	pushd ${commonPath}

	echo common: `git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
	mvn clean install -Dmaven.test.skip=true
	pk_result=$?
	popd
	if [ $pk_result -ne 0 ]
	then
		echo '------common包打失败(终止打包流程)--------'
		exit 1;
	fi
        echo [INFO]--------common包已打包完成---------------
	return 0;
}


#######打包充值服
function package_current_charge(){
	packageName='charge'
    echo [INFO]--------打包${packageName}包开始-----------------
    pushd ${chargePath}

	echo charge:`git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
    mvn clean install -Dmaven.test.skip=true
    pk_result=$?

    echo '[INFO]开始拷贝文件>>>>>>>>>>>>>>'

    rm -rf $distCharge/*
    mkdir -p $distCharge/WEB-INF/lib
    cp -rf src/main/webapp/* $distCharge/
    cp -rf target/lib/* $distCharge/WEB-INF/lib/
    cp -rf target/*.jar $distCharge/WEB-INF/lib/

    version=$(git log --pretty=format:"%H" -1)
    echo $version > $distCharge/record.txt

    echo '[INFO]--------${packageName}包已拷贝完成<<<<<<<<<<<<<'

    popd
    if [ $pk_result -ne 0 ]
    then
            echo '------${packageName}包打失败(终止打包流程)--------'
            exit 1;
    fi
    echo [INFO]--------${packageName}包已打包完成---------------
    return 0;
}


#######打包登录服
function package_current_login(){
	packageName='login'
    echo [INFO]--------打包${packageName}包开始-----------------
    pushd ${loginPath}

	echo charge:`git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`

    mvn -Dmaven.test.skip=true clean package install
    pk_result=$?

    rm -rf $distLogin/*
    mkdir -p $distLogin/WEB-INF/lib
    cp -rf src/main/webapp/* $distLogin/
    cp -rf target/lib/* $distLogin/WEB-INF/lib/
    cp -rf target/*.jar $distLogin/WEB-INF/lib/

    version=$(git log --pretty=format:"%H" -1)
    echo $version > $distLogin/record.txt

    popd
    if [ $pk_result -ne 0 ]
    then
            echo '------${packageName}包打失败(终止打包流程)--------'
            exit 1;
    fi
    echo [INFO]--------${packageName}包已打包完成---------------
    return 0;
}

#######打包proxy
function package_current_proxy(){
    packageName='proxy'
    echo [INFO]--------打包${packageName}包开始-----------------
    pushd ${proxyPath}

	echo proxy:`git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`

    mvn clean package install -Dmaven.test.skip=true

    pk_result=$?

    popd
    if [ $pk_result -ne 0 ]
    then
            echo '------${packageName}包打失败(终止打包流程)--------'
            exit 1;
    fi
    echo [INFO]--------${packageName}包已打包完成---------------
    return 0;
}

#######打包管理后台
function package_current_manager(){
	packageName='manager'
    echo [INFO]--------打包${packageName}包开始-----------------
    pushd ${managerPath}

	echo charge:`git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
    mvn clean package install -Dmaven.test.skip=true
    pk_result=$?

    rm -rf $distManager/*
    mkdir -p $distManager/WEB-INF/lib
    cp -rf src/main/webapp/* $distManager/
    cp -rf target/lib/* $distManager/WEB-INF/lib
    cp -rf target/*.jar $distManager/WEB-INF/lib

    version=$(git log --pretty=format:"%H" -1)
    echo $version > $distManager/record.txt
    popd
    if [ $pk_result -ne 0 ]
    then
            echo '------${packageName}包打失败(终止打包流程)--------'
            exit 1;
    fi
    echo [INFO]--------${packageName}包已打包完成---------------
    return 0;
}

#######打包礼包服
function package_current_gift(){
	packageName='gift'
    echo [INFO]--------打包${packageName}包开始-----------------
    pushd ${giftPath}

	echo charge:`git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
    mvn clean package install -Dmaven.test.skip=true
    pk_result=$?

    rm -rf $distGift/*
    mkdir -p $distGift/WEB-INF/classes
    mkdir -p $distGift/WEB-INF/lib
    cp -rf src/main/webapp/* $distGift/
    cp -rf src/main/resources/* $distGift/WEB-INF/classes
    cp -rf target/lib/* $distGift/WEB-INF/lib
    cp -rf target/*.jar $distGift/WEB-INF/lib

    version=$(git log --pretty=format:"%H" -1)
    echo $version > $distGift/record.txt

    popd
    if [ $pk_result -ne 0 ]
    then
            echo '------${packageName}包打失败(终止打包流程)--------'
            exit 1;
    fi
    echo [INFO]--------${packageName}包已打包完成---------------
    return 0;
}

#######打包game-server
function package_current_server(){
    packageName='game-server'
    echo [INFO]--------打包${packageName}包开始-----------------
    pushd ${serverPath}
    echo server:`git log --pretty=format:"%n %h %cd %n %an %ae %n %s %b %N %n" -1`
    mvn clean package install -Dmaven.test.skip=true
    pk_result=$?

    mkdir -p ${distServer}/resources
    mkdir -p ${distServer}/lib
    cp -rf ./game-server/target/resources/* $distServer/resources/
    cp -rf ./game-server/target/lib/* $distServer/lib/
    cp -rf ./game-server/target/*.jar $distServer/

    mkdir -p ${distCenter}/resources
    mkdir -p ${distCenter}/lib
    cp -rf ./center-server/target/resources/* $distCenter/resources/
    cp -rf ./center-server/target/lib/* $distCenter/lib/
    cp -rf ./center-server/target/*.jar $distCenter/

    mkdir -p ${distFight}/resources
    mkdir -p ${distFight}/lib
    cp -rf ./battle-server/target/resources/* $distFight/resources/
    cp -rf ./battle-server/target/lib/* $distFight/lib/
    cp -rf ./battle-server/target/*.jar $distFight/

    mkdir -p ${distReport}/resources
    mkdir -p ${distReport}/lib
    cp -rf ./report-server/target/resources/* $distReport/resources/
    cp -rf ./report-server/target/lib/* $distReport/lib/
    cp -rf ./report-server/target/*.jar $distReport/

    mkdir -p ${distMerge}/resources
    mkdir -p ${distMerge}/lib
    cp -rf ./merge-server/target/resources/* $distMerge/resources/
    cp -rf ./merge-server/target/lib/* $distMerge/lib/
    cp -rf ./merge-server/target/*.jar $distMerge/

    version=$(git log --pretty=format:"%H" -1)
    echo $version > $distServer/record.txt
    echo $version > $distCenter/record.txt
    echo $version > $distFight/record.txt
    echo $version > $distReport/record.txt
    echo $version > $distMerge/record.txt

    popd
    if [ $pk_result -ne 0 ]
    then
            echo '------${packageName}包打失败(终止打包流程)--------'
            exit 1;
    fi
    echo [INFO]--------${packageName}包已打包完成---------------
    return 0;
}

function pack_all() {
    ## 打包sdk
    package_current_sdk
    ###打包common包
    package_current_common
    ###打包充值服
    package_current_charge
    ###打包登录
    package_current_login
    ##打包代理
    package_current_proxy
    ###打包管理后台
    package_current_manager
    ###礼包服
    package_current_gift
    ###打包游戏服
    package_current_server

}
function pack_core(){
    ## 打包sdk
    package_current_sdk
    ###打包common包
    package_current_common
}

function pack_charge() {
    pack_core

    ###打包充值服
    package_current_charge
}

function pack_login(){

    ###打包登录
    package_current_login
}

function pack_manager(){
    ###打包代理
    package_current_manager
}

function pack_gift(){
    ###礼包服
    package_current_gift

}
function pack_server(){
    ###打包游戏服
    package_current_server
}

case $1 in
 charge)
        pack_charge
        ;;
 login)
        pack_login
        ;;
 gift)
        pack_gift
        ;;
 proxy)
        package_current_proxy
        ;;
 manager)
        pack_manager
        ;;
 server)
        pack_server
        ;;
 *)
        echo "Usage: [charge|login|gift|proxy|manager|server]"
        pack_all
        exit 0
        ;;
esac
exit 0