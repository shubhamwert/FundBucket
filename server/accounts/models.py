from django.db import models
from django.contrib.auth.models import User
# Create your models here.
class PersonalInfo(models.Model):
    user = models.OneToOneField(User,on_delete=models.CASCADE,related_name='personal', primary_key=True)
    mobile_number = models.IntegerField(null=True)
    address = models.CharField(max_length=100, blank=True)
    city = models.CharField(max_length=50,blank=True)
    state = models.CharField(max_length=20, blank=True)
    account_number = models.IntegerField(null=True)
    bank_ifsc = models.CharField(verbose_name='Bank IFSC Code', max_length=30, blank=True)   
    profile_pic = models.ImageField(default='profile_pic/user_default.png', upload_to='profile_pic/') 
    # registered = models.BooleanField(default=False)