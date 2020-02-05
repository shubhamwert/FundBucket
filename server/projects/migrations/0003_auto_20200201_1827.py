# Generated by Django 3.0 on 2020-02-01 18:27

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('projects', '0002_post_profile_pic'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='post',
            name='profile_pic',
        ),
        migrations.AddField(
            model_name='post',
            name='picture',
            field=models.ImageField(null=True, upload_to='profile_pic/'),
        ),
    ]