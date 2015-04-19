package my.company;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christoph on 18.04.15.
 */
public class RadioGroupPage extends WebPage {

    /**
     * Constructor
     */
    public RadioGroupPage() {

        List<Person> personList = new ArrayList<Person>();
        Person person1 = new Person();
        person1.setLastName("AAA");
        person1.setName("aaa");

        Person person2 = new Person();
        person2.setLastName("BBB");
        person2.setName("bbb");

        Person person3 = new Person();
        person3.setLastName("CCC");
        person3.setName("ccc");

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);


        final RadioGroup<Person> group = new RadioGroup<Person>("group", new Model<Person>());
        Form<?> form = new Form("form") {
            @Override
            protected void onSubmit() {
                info("selected person: " + group.getDefaultModelObjectAsString());
            }
        };
        group.add(new AjaxFormChoiceComponentUpdatingBehavior() {
            protected void onUpdate(AjaxRequestTarget target) {
                // Ajax actions here
                System.out.println("The selected value is " + getComponent().getDefaultModelObjectAsString());
            }
        }).setOutputMarkupId(true);
        group.setOutputMarkupId(true);
        this.setOutputMarkupId(true);
        form.setOutputMarkupId(true);
        add(form);
        form.add(group);

        ListView<Person> persons = new ListView<Person>("persons",
                personList) {

            @Override
            protected void populateItem(ListItem<Person> item) {
                item.add(new Radio<Person>("radio", item.getModel()));
                item.add(new Label("name",
                        new PropertyModel<String>(item.getDefaultModel(), "name")));
                item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
                        "lastName")));
                item.setOutputMarkupId(true);
            }

        };

        group.add(persons);

        add(new FeedbackPanel("feedback"));
    }


}
