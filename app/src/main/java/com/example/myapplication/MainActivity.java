package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends Activity {

    private Button btn = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3 = new Intent();
                intent_test3.setClass(MainActivity.this, test3_simpleAdapter.class);
                MainActivity.this.startActivity(intent_test3);
            }
        });

        btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3_alertDialog = new Intent();
                intent_test3_alertDialog.setClass(MainActivity.this, test3_alertDialog.class);
                MainActivity.this.startActivity(intent_test3_alertDialog);
            }
        });

        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3_customMenu = new Intent();
                intent_test3_customMenu.setClass(MainActivity.this, test3_customMenu.class);
                MainActivity.this.startActivity(intent_test3_customMenu);
            }
        });

        btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_test3_actionMode = new Intent();
                intent_test3_actionMode.setClass(MainActivity.this, test3_actionMode.class);
                MainActivity.this.startActivity(intent_test3_actionMode);
            }
        });

        Set<String> set = getIpAddress();
        if(set.size() > 0) {
//        	System.out.println(set);
            set.forEach(ip -> {
                Toast.makeText(MainActivity.this, ip, Toast.LENGTH_SHORT).show();
                System.out.println("本机ip: " + ip);
            });
        }
        set.remove("127.0.0.1");
        scannerNetwork(set);
        System.out.println("扫描完毕...");
//        System.exit(0);
    }

    private static Set<String> getIpAddress() {
        Set<String> ipList = new HashSet<>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                // 排除虚拟接口和没有启动运行的接口
                if (netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
//                        if (ip != null && (ip instanceof Inet4Address || ip instanceof Inet6Address)) {
//                            ipList.add(ip.getHostAddress());
//                        }
                        if (ip != null && (ip instanceof Inet4Address)) {
                            ipList.add(ip.getHostAddress());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void scannerNetwork(Set<String> set) {
        try {
            set.forEach(address -> {
                // 设置IP地址网段
                String ips = getNetworkSegment(address);

                System.out.println("开始扫描 " + ips + "网段...");

                String ip;
                InetAddress addip = null;
                // 遍历IP地址
                for (int i = 1; i < 255; i++) {
                    ip = ips + i;
                    try {
                        addip = InetAddress.getByName(ip);
                    } catch (UnknownHostException e) {
                        System.out.println("找不到主机: " + ip);
                    }
                    // 获取登录过的设备
                    if (!ip.equals(addip.getHostName())) {
                        try {
                            // 检查设备是否在线，其中1000ms指定的是超时时间
                            boolean status = InetAddress.getByName(addip.getHostName()).isReachable(1000); // 当返回值是true时，说明host是可用的，false则不可。
                            System.out.println("IP地址为:" + ip + "\t\t设备名称为: " + addip.getHostName() + "\t\t是否可用: "
                                    + (status ? "可用" : "不可用"));
                            Toast.makeText(MainActivity.this, "IP地址为:" + ip + "\t\t设备名称为: " + addip.getHostName() + "\t\t是否可用: "
                                    + (status ? "可用" : "不可用"), Toast.LENGTH_SHORT).show();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception uhe) {
            System.err.println("Unable to find: " + uhe.getLocalizedMessage());
        }
    }

    private static String getNetworkSegment(String ip) {
        int startIndex = ip.lastIndexOf(".");
        return ip.substring(0, startIndex+1);
    }

}