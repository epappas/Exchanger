/**
 * 
 */
package com.evalonlabs.blog.concurrent.exchanger;

import java.util.Queue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Evangelos Pappas - Evalonlabs.com
 */
public abstract class Messenger<T> implements MessageTrader<Exchangeable<T>>,
		Runnable
{
	private final Queue<Exchangeable<T>>		queue;
	private final Exchanger<Exchangeable<T>>	exchanger;
	
	public Messenger(Exchanger<Exchangeable<T>> exchanger)
	{
		queue = new LinkedBlockingQueue<Exchangeable<T>>();
		this.exchanger = exchanger;
	}
	
	public Messenger(Exchanger<Exchangeable<T>> exchanger, T... messages)
	{
		this(exchanger);
		for (int i = messages.length - 1; i > 0; --i)
		{
			this.queue.add(new DirectExchangeable<T>(messages[i]));
		}
	}
	
	public void add(T message)
	{
		this.queue.add(new DirectExchangeable<T>(message));
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				this.onExchange(this.exchanger.exchange(this.queue.poll()));
			}
			catch (Exception e)
			{
				
			}
		}
	}
	
	public abstract void onExchange(Exchangeable<T> message);
}
