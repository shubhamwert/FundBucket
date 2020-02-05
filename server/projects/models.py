from django.db import models
from django.contrib.auth.models import User
from django.utils import timezone
# Create your models here.
class Category(models.Model):
    category = models.CharField(max_length=20)

    def __str__(self):
        return self.category

class Post(models.Model):
    category = models.ForeignKey(Category, on_delete= models.CASCADE)
    author = models.ForeignKey(User, on_delete = models.CASCADE)
    title = models.CharField(max_length=100)
    description = models.TextField(max_length=255)
    published_date = models.DateTimeField(default=timezone.now)
    deadline = models.DateField(blank=True, null=True)
    funds = models.IntegerField(default=0)
    total_funds = models.IntegerField()
    picture = models.ImageField(upload_to='post_picture/',blank=True, null=True)
    
    def add_funds(self, value):
        self.funds += value
    
    def deduct_funds(self, value):
        if value > self.funds:
            return False
        else:
            self.funds -= value
            return True

    def __str__(self):
        return self.title

class Comment(models.Model):
    post = models.ForeignKey(Post, related_name='comments', on_delete= models.CASCADE)
    author = models.ForeignKey(User, on_delete=models.CASCADE)
    text = models.TextField(verbose_name='Comment', max_length=100)

    def __str__(self):
        return self.text