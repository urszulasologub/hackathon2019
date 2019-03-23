from django.conf.urls import url
from django.urls import path
from . import views

urlpatterns = [
    path("quiz/<quiz_pk>", views.QuestionList.as_view(), name="quiz_read"),
    path("group/<group_pk>/<quiz_pk>/manage", views.quiz_display, name="quiz_display"),
    path("group/<group_pk>", views.group_display, name='group_display'),

]
