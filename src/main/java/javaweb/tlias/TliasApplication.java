//开发模式：前后端分离
//具体的开发流程：需求分析->接口设计->前后端分离开发->测试->联调
//前后端采用Restful风格的接口（即每个接口的URL都表示一个资源，而HTTP方法则表示对该资源的操作，如GET、POST、PUT、DELETE等）


//写项目前的环境搭建
//1.创建Springboot项目，并引入web依赖、mybatis依赖、mysql驱动依赖、lombok依赖
//2.创建数据库Dept，配置application.yml文件，连接数据库
//3.准备基础代码结构并引入实体类Dept和响应结果类Result


//Nginx反向代理配置（安全、负载均衡、高可用、缓存等）
//1.在Nginx配置文件中添加反向代理配置，将所有请求都转发到Springboot应用的端口（如8080）
//2.重启Nginx服务，使配置生效

package javaweb.tlias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 扫描并注册filter1过滤器
@ServletComponentScan("javaweb.tlias.filter")
@SpringBootApplication
public class TliasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TliasApplication.class, args);
	}

}
1
