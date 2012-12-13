package com.evalonlabs.blog.concurrent.exchanger;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Evangelos Pappas - Evalonlabs.com
 */
public class DirectExchangeable<T> implements Exchangeable<T>
{
	private final AtomicReference<T>	reference;
	
	public DirectExchangeable(T target)
	{
		this.reference = new AtomicReference<T>(target);
	}
	
	public T get()
	{
		return this.reference.get();
	}
	
}
