import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReusComponent } from './reus.component';

describe('ReusComponent', () => {
  let component: ReusComponent;
  let fixture: ComponentFixture<ReusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ReusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
