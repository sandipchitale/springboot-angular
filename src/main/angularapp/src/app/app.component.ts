import {AfterViewInit, Component, Inject, OnInit} from '@angular/core';
import {DOCUMENT} from '@angular/common';

import {FormsModule} from '@angular/forms';

import {ButtonModule} from 'primeng/button';
import {TableModule} from 'primeng/table';
import {TabsModule} from 'primeng/tabs';
import {ToolbarModule} from 'primeng/toolbar';

import {ToggleButtonModule} from 'primeng/togglebutton';
import {AppService} from './app.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ButtonModule, FormsModule, TableModule, TabsModule, ToggleButtonModule, ToolbarModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  lightTheme = true;

  beanDefinitionNames: string[] = [];
  configurationProperties: string[] = [];

  constructor(@Inject(DOCUMENT) private document: Document,
              @Inject(AppService) private apiService: AppService) {
  }

  ngOnInit(): void {
    this.apiService.beanDefinitionNames().subscribe((beanDefinitionNames: string[]) => {
      this.beanDefinitionNames = beanDefinitionNames;
    });
    this.apiService.configurationProperties().subscribe((configurationProperties: string[]) => {
      this.configurationProperties = configurationProperties;
    });
  }

  adjustTheme(event?: any) {
    if (this.document.body.classList.contains('dark-theme')) {
      this.document.body.classList.remove('dark-theme');
    } else {
      this.document.body.classList.add('dark-theme');
    }
  }
}
