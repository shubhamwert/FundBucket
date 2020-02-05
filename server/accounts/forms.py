from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from accounts import models
#Accounts Forms

class UserCreateForm(UserCreationForm):
    class Meta():
        model = User
        fields = ['username', 'first_name', 'last_name', 'email', 'password1', 'password2']

class PersonalInfoForm(forms.ModelForm):
    class Meta():
        model = models.PersonalInfo
        fields = ['profile_pic','mobile_number', 'address', 'city', 'state', 'account_number', 'bank_ifsc']