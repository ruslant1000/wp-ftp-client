package kz.tem.portal.ftpclient;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import kz.msystem.commons.socket.processor.java.ftp.FileRecord;
import kz.tem.portal.explorer.panel.common.table.AbstractTable;
import kz.tem.portal.server.plugin.Module;
import kz.tem.portal.server.plugin.ModuleConfig;

@SuppressWarnings("serial")
public class FtpTable extends Module {

	// public static final String SOCKET_SERVER_HOST = "Socket server host";
	// public static final String SOCKET_SERVER_PORT = "Socket server port";
	public static final String FTP_HOST = "FTP host";
	public static final String FTP_PORT = "FTP port";
	public static final String FTP_USER = "FTP user";
	public static final String FTP_PASSWORD = "FTP password";
	public static final String FTP_PATH = "FTP path";

	public static final String CAN_UPLOAD = "Can upload";
	public static final String CAN_DELETE = "Can delete";
	public static final String CAN_NEW_FOLDER = "Can create folder";

	// private String socketServerHost;
	// private String socketServerPort;
	private String host;
	private String port;
	private String user;
	private String password;
	private String path;

	private boolean canUpload = true;
	private boolean canDelete = true;
	private boolean canNewFolder = true;

	private List<String> dirs = new LinkedList<String>();
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm");

	public FtpTable(String id, ModuleConfig config) {
		super(id, config);
		// socketServerHost =
		// getModuleConfig().getValues().get(SOCKET_SERVER_HOST);
		// socketServerPort =
		// getModuleConfig().getValues().get(SOCKET_SERVER_PORT);
		host = getModuleConfig().getValues().get(FTP_HOST);
		port = getModuleConfig().getValues().get(FTP_PORT);
		user = getModuleConfig().getValues().get(FTP_USER);
		password = getModuleConfig().getValues().get(FTP_PASSWORD);
		path = getModuleConfig().getValues().get(FTP_PATH);

		canUpload = Boolean.parseBoolean(getModuleConfig().getBooles().get(
				CAN_UPLOAD));
		canDelete = Boolean.parseBoolean(getModuleConfig().getBooles().get(
				CAN_DELETE));
		canNewFolder = Boolean.parseBoolean(getModuleConfig().getBooles().get(
				CAN_NEW_FOLDER));
	}

	public String getCurrentPath() {
		String p = path;
		for (String dir : dirs)
			p = p + "/" + dir;
		return p.replaceAll("//", "/");
	}

	private AbstractTable<FileRecord> table;
	private List<FileRecord> list;

	@Override
	public void create() throws Exception {
		FtpTable.this.removeAll();

		add(new kz.tem.portal.explorer.panel.common.ftp.FtpTable("table", host, port, user, password, path, canUpload, canDelete, canNewFolder));
		
	}


	@Override
	public void initDefaultConfigs(ModuleConfig config) throws Exception {
		super.initDefaultConfigs(config);
		// config.addDefaultConfig(SOCKET_SERVER_HOST, "195.189.30.203");
		// config.addDefaultConfig(SOCKET_SERVER_PORT, "8888");
		config.addDefaultConfig(FTP_HOST, "192.168.0.213");
		config.addDefaultConfig(FTP_PORT, "21");
		config.addDefaultConfig(FTP_USER, "admin");
		config.addDefaultConfig(FTP_PASSWORD, "admin");
		config.addDefaultConfig(FTP_PATH, "/");

		config.addDefaultConfig(CAN_DELETE, false);
		config.addDefaultConfig(CAN_NEW_FOLDER, true);
		config.addDefaultConfig(CAN_UPLOAD, true);

	}

	

}
