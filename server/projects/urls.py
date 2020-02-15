from django.urls import path
from . import views
from FundBucket import settings
from django.conf.urls.static import static
# import BlockChain.contract_interface as ci

app_name = 'projects'

urlpatterns = [
    path('', views.PostListView.as_view(), name = 'post_list'),
    path('post_detail/<int:pk>/',  views.PostDetailView.as_view(), name = 'post_detail'),
    path('post_delete/<int:pk>/', views.PostDeleteView.as_view(), name ='post_delete'),
    path('post_create/', views.PostCreateView.as_view(), name='post_create'),
    path('post/<int:pk>/comment', views.put_comment_on_post, name='comment_create'),
    path('user_posts/', views.UserPostView.as_view(), name='user_posts'),
    path('post/<int:pk>/funds', views.add_funds_view, name='add_funds'),
    # TODO
    path('checkconnectivity/', views.checkconnectivity, name='checkconnectivity'),
    path('registerCampaign/', views.CreateCampaign, name='registerCampaign'),
    path('registerFunder/', views.CreateFunderForBucket, name='registerFunder'),
    # path('registerFundSeeker/', views.registerFundSeeker, name='registerFundSeeker'),
    path('getFundSeeker/', views.getFundSeeker, name='getFundSeeker'),
    path('sendMoneyToFundSeeker/', views.sendMoneyToFundSeeker, name='sendMoneyToFundSeeker'),
    path('startVotingFor/', views.startVotingFor, name='startVotingFor'),
    path('endVotingFor/', views.endVotingFor, name='endVotingFor'),
    path('voteFor/', views.vote, name='voteFor'),
    path('isAllowedToWithDraw/', views.isAllowedToWithDraw, name='isAllowedToWithDraw'),
    path('getCurrentFundingStageFor/', views.getCurrentFundingStageFor, name='getCurrentFundingStageFor'),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)