
#设置构建本机库所需的CMake的最小版本
cmake_minimum_required(VERSION 3.4.1)

#创建并命名一个库，将其设置为静态
#或SHARED，并提供源代码的相对路径
#你可以定义多个库，CMake为你构建他们
#Gradle自动将共享库打包到APK中

add_library( # 设置库的名称
             native-jni-lib
             # 将库设置为公共库
             SHARED
             # 提供到源文件的相对路径
        native-jni-lib.cpp)

# 搜索指定的预构件库，并将路径存储为变量
# 因为CMake在搜索路径中默认包含系统库，所以您只需要指定要添加的公共NDK库的名称
#CMake在完成库的构建之前验证库是否存在

find_library( # 设置路径变量的名称
              log-lib

              # 指定你想要CMake定位的NDK库的名称
              log )

# 指定CMake应该链接到目标库的库
# 您可以链接多个库，例如在此构建脚本中定义的库、预构建的第三方库或系统库

target_link_libraries( # 指定目标库
                       native-jni-lib
                       #  将目标库链接到包含在NDK中的日志库
                       ${log-lib} )