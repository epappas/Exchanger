/**
 * 
 */
package com.evalonlabs.blog.concurrent.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author Evangelos Pappas - Evalonlabs.com
 */
public class Main
{
	
	/**
	 * @param args
	 * @author Evangelos Pappas - Evalonlabs.com
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Exchanger<Exchangeable<Long>> exchanger = new Exchanger<Exchangeable<Long>>();
		
		final Messenger<Long> messenger1 = new Messenger<Long>(exchanger)
		{
			@Override
			public void onExchange(Exchangeable<Long> message)
			{
				System.out.println("Messenger[1] @ " + System.nanoTime()
						+ " Received: " + message.get());
			}
		};
		final Messenger<Long> messenger2 = new Messenger<Long>(exchanger)
		{
			@Override
			public void onExchange(Exchangeable<Long> message)
			{
				System.out.println("Messenger[2] @ " + System.nanoTime()
						+ " Received: " + message.get());
			}
		};
		
		new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					messenger1.add(System.nanoTime());
				}
			}
		}, "Sink[1]").start();
		new Thread(new Runnable()
		{
			public void run()
			{
				while (true)
				{
					messenger2.add(System.nanoTime());
				}
			}
		}, "Sink[2]").start();
		new Thread(messenger1, "Messenger[1]").start();
		new Thread(messenger2, "Messenger[2]").start();
	}
}
