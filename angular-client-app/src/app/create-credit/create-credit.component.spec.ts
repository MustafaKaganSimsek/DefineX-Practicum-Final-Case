import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCreditComponent } from './create-credit.component';

describe('CreateCreditComponent', () => {
  let component: CreateCreditComponent;
  let fixture: ComponentFixture<CreateCreditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateCreditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCreditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
