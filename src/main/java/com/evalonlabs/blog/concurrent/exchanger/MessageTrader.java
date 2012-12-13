/**
 * 
 */
package com.evalonlabs.blog.concurrent.exchanger;

/**
 * @author Evangelos Pappas - Evalonlabs.com
 */
public interface MessageTrader<T extends Exchangeable<?>>
{
	void onExchange(T message);
}
