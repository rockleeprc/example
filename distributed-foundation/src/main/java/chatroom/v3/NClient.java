package chatroom.v3;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;

/**
 * Description:
 * <br/>Copyright (C), 2008-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class NClient
{
	//������SocketChannel��Selector����
	private Selector selector = null;
	//���崦�����ͽ�����ַ���
	private Charset charset = Charset.forName("UTF-8");
	//�ͻ���SocketChannel
	private SocketChannel sc = null;
	public void init()throws IOException
	{
		selector = Selector.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 9999);
		//����open��̬�����������ӵ�ָ��������SocketChannel
		sc = SocketChannel.open(isa);
		//���ø�sc�Է�������ʽ����
		sc.configureBlocking(false);
		//��SocketChannel����ע�ᵽָ��Selector
		sc.register(selector, SelectionKey.OP_READ);
		//������ȡ�����������ݵ��߳�
		new ClientThread().start();
		//��������������
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine())
		{
			//��ȡ��������
			String line = scan.nextLine();
			//��������������������SocketChannel��
			sc.write(charset.encode(line));
		}
	}
	//�����ȡ���������ݵ��߳�
	private class ClientThread extends Thread
	{
		public void run()
		{
			try
			{
				while (selector.select() > 0) 
				{
					//����ÿ���п���IO����Channel��Ӧ��SelectionKey
					for (SelectionKey sk : selector.selectedKeys())
					{
						//ɾ�����ڴ����SelectionKey
						selector.selectedKeys().remove(sk);
						//�����SelectionKey��Ӧ��Channel���пɶ�������
						if (sk.isReadable())
						{
							//ʹ��NIO��ȡChannel�е�����
							SocketChannel sc = (SocketChannel)sk.channel();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							String content = "";
							while(sc.read(buff) > 0)
							{
								sc.read(buff); 
								buff.flip();
								content += charset.decode(buff);
							}
							//��ӡ�����ȡ������
							System.out.println("������Ϣ��" + content);
							//Ϊ��һ�ζ�ȡ��׼��
							sk.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

    public static void main(String[] args)
		throws IOException
	{
		new NClient().init();
    }
}