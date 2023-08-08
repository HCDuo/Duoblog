# 使用包含预装了nginx的基础镜像
FROM nginx:latest

# 设置工作目录
WORKDIR /app

# 向镜像中添加文件：我们前端项目构建出来的dist目录下的静态资源文件
COPY dist/ /app/html

# 向镜像中添加文件：nginx配置文件
COPY nginx.app.conf /etc/nginx/conf.d/app.conf

# 声明容器将对外暴露8080端口
EXPOSE 8080
