from django import forms
from . import models

class PostForm(forms.ModelForm):

    class Meta():
        model = models.Post
        fields = ['category', 'title', 'description', 'total_funds', 'deadline', 'picture']

class CommentForm(forms.ModelForm):
    class Meta():
        model = models.Comment
        fields = ['text']

class AddFunds(forms.Form):
    amount = forms.IntegerField()
    