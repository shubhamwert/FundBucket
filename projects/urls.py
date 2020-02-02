from django.urls import path
from . import views
from FundBucket import settings
from django.conf.urls.static import static

app_name = 'projects'

urlpatterns = [
    path('', views.PostListView.as_view(), name = 'post_list'),
    path('post_detail/<int:pk>/',  views.PostDetailView.as_view(), name = 'post_detail'),
    path('post_delete/<int:pk>/', views.PostDeleteView.as_view(), name ='post_delete'),
    path('post_create/', views.PostCreateView.as_view(), name='post_create'),
    path('post/<int:pk>/comment', views.put_comment_on_post, name='comment_create'),
    path('user_posts/', views.UserPostView.as_view(), name='user_posts'),
    path('post/<int:pk>/funds', views.add_funds_view, name='add_funds')
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)