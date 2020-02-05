from django.shortcuts import render, redirect, get_object_or_404
from django.urls import reverse_lazy
from django.views.generic import CreateView, TemplateView, FormView
from accounts import forms
from django.contrib.auth.mixins import LoginRequiredMixin
from django.http import HttpResponseRedirect
from . import models
from django.contrib.auth.models import User
# Create your views here.

class SignUp(CreateView):
    template_name = 'accounts/signup.html'
    form_class = forms.UserCreateForm
    success_url = reverse_lazy('accounts:personal_info')

class PersonalInfoView(LoginRequiredMixin, CreateView):
    login_url = reverse_lazy('accounts:login')
    template_name = 'accounts/personal_info_form.html'
    form_class = forms.PersonalInfoForm
    success_url = reverse_lazy('projects:post_list')

    def get(self, request):
        if getattr(request.user, 'personal', None):
            return redirect('projects:post_list')
        else:
            form = forms.PersonalInfoForm()
            return render(request, 'accounts/personal_info_form.html', context={'form':form})

    def post(self, request):
        form = forms.PersonalInfoForm(request.POST, request.FILES)
        model = models.PersonalInfo(request.POST, request.FILES)
        if form.is_valid():
            model = form.save(commit=False)
            model.user = request.user
            model.save()
            return redirect('projects:post_list')
    # def form_valid(self, form):
    #     form.instance.user = self.request.user
    #     form.instance.profile_pic = self.request.FILES.getlist('profile_pic')
    #     print(form.instance.profile_pic)
    #     # return super().form_valid(form)

def profile_view(request, username):
    profile_user = get_object_or_404(User, username=username)
    return render(request, 'accounts/profile.html', context={'profile_user':profile_user})