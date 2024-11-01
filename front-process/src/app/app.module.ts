import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReusComponent } from './components/reus/reus.component';
import { ProcessosComponent } from './components/processos/processos.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { ProcessoService } from '../shared/services/processo.service';
import { ReuService } from '../shared/services/reu.service';

@NgModule({
  declarations: [
    AppComponent,
    ReusComponent,
    ProcessosComponent,
    FooterComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    ProcessoService,
    ReuService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
