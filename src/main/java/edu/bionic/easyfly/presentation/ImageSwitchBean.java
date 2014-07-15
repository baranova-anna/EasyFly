package edu.bionic.easyfly.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class ImageSwitchBean implements Serializable {

	private List<String> images;

	public ImageSwitchBean() {
		images = new ArrayList<String>();
		images.add("city1.jpg");
		images.add("city2.jpg");
		images.add("city3.jpg");
		images.add("city4.jpg");
		images.add("city5.jpg");
		images.add("city6.jpg");
		images.add("city7.jpg");
		images.add("city8.jpg");
		images.add("city9.jpg");
		images.add("city10.jpg");
	}

	public List<String> getImages() {
		return images;
	}

}
