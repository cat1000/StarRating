package my.company;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public abstract class StarBehavior extends AjaxFormChoiceComponentUpdatingBehavior
{
	private static ResourceReference JS = new JavaScriptResourceReference(StarBehavior.class,
			"js/jquery.rating.js");
	private static ResourceReference CSS = new CssResourceReference(StarBehavior.class,
			"js/jquery.rating.css");
	
	@Override
	public void renderHead(Component component, IHeaderResponse response)
	{
		super.renderHead(component, response);
		
		response.render(JavaScriptHeaderItem.forReference(JS));
		response.render(CssHeaderItem.forReference(CSS));
		
		FormComponent<?> formComponent = getFormComponent();
		
		String selector = "[name=\"" + formComponent.getInputName() + "\"]";
		
		response.render(OnDomReadyHeaderItem.forScript(String.format("$('%s').rating({ callback: function(value, link) { $(this).trigger('click'); }});", selector)));
	}
}