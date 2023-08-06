import { AfterViewInit, Component, Inject, OnInit } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import {AppService} from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, AfterViewInit {
  lightTheme = true;

  beanDefinitionNames: string[] = [];
  configurationProperties: string[] = [];

  constructor(@Inject(DOCUMENT) private document: Document, private apiService: AppService) {}

  ngOnInit(): void {
    this.apiService.beanDefinitionNames().subscribe((beanDefinitionNames: string[]) => {
      this.beanDefinitionNames = beanDefinitionNames;
    });
    this.apiService.configurationProperties().subscribe((configurationProperties: string[]) => {
      this.configurationProperties = configurationProperties;
    });
  }

  ngAfterViewInit(): void {
    this.adjustTheme();
  }

  adjustTheme(event?: any) {
    let theme = 'light-theme';
    if (this.lightTheme) {
      theme = 'light-theme';
    } else {
      theme = 'dark-theme';
    }
    const themeLink = this.document.getElementById('app-theme') as HTMLLinkElement;
    if (themeLink) {
      themeLink.href = theme + '.css';
    }
  }

}
