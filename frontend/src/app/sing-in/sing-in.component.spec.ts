import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingINComponent } from './sing-in.component';

describe('SingINComponent', () => {
  let component: SingINComponent;
  let fixture: ComponentFixture<SingINComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SingINComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingINComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
