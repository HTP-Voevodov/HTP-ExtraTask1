package by.htp.extratask1.bean;

public class News {
	private String category;
	private String subCategory;
	private String newsName;
	private String provider;
	private String dateOfIssue;
	private String newsBody;
	
	public News() {}
	
	public String getCategory() {
		return category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public String getNewsName() {
		return newsName;
	}
	public String getProvider() {
		return provider;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public String getNewsBody() {
		return newsBody;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public void setNewsBody(String newsBody) {
		this.newsBody = newsBody;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
		result = prime * result + ((newsBody == null) ? 0 : newsBody.hashCode());
		result = prime * result + ((newsName == null) ? 0 : newsName.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((subCategory == null) ? 0 : subCategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (dateOfIssue == null) {
			if (other.dateOfIssue != null)
				return false;
		} else if (!dateOfIssue.equals(other.dateOfIssue))
			return false;
		if (newsBody == null) {
			if (other.newsBody != null)
				return false;
		} else if (!newsBody.equals(other.newsBody))
			return false;
		if (newsName == null) {
			if (other.newsName != null)
				return false;
		} else if (!newsName.equals(other.newsName))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (subCategory == null) {
			if (other.subCategory != null)
				return false;
		} else if (!subCategory.equals(other.subCategory))
			return false;
		return true;
	}

}
