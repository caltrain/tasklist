package com.iluwatar.tasklist.web;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iluwatar.tasklist.web.page.DashboardPage;
import com.iluwatar.tasklist.web.page.LoginPage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.less.BootstrapLess;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.iluwatar.tasklist.Start#main(String[])
 */
public class TasklistApplication extends AuthenticatedWebApplication
{    	
	
	final static Logger logger = LoggerFactory.getLogger(TasklistApplication.class);
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return DashboardPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

        SpringComponentInjector spring = newInjector();
		getComponentInstantiationListeners().add(spring);
		getBehaviorInstantiationListeners().add(spring);
		spring.inject(this);
		
		Bootstrap.install(this, new BootstrapSettings());
		BootstrapLess.install(this);
		
		this.getMarkupSettings().setStripWicketTags(true);
	}
	
	protected SpringComponentInjector newInjector() {
	    return new SpringComponentInjector(this);
	}

	@Override
	public Session newSession(Request request, Response response) {
		TasklistSession session = new TasklistSession(request);
		return session;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return TasklistSession.class;
	}
	
}
