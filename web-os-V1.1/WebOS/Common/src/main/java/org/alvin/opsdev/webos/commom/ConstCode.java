package org.alvin.opsdev.webos.commom;

public interface ConstCode {
	//协议
	byte PROTO_RDP = 1;
	byte PROTO_VNC = 2;
	byte PROTO_SSH = 3;
	byte PROTO_IMPI = 4;
	byte PROTO_SSH2_SFTP = 5;
	//状态
	byte STAUS_DISABLED = 0;
	byte STAUS_ENABLED = 1;
	//角色
	byte ROLE_ROOT = 0; //超级管理员
	byte ROLE_OPS = 1;//运维人员
	byte ROLE_MEMBER = 2;//普通用户
	//图标类型
	byte APP_TYPE_DESKTOP = 0;
	byte APP_TYPE_START_MENU = 1;
	//
	byte DICT_CUSTOM_KEY = 1; //用户自定义参数
}
