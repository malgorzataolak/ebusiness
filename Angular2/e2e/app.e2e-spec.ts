import { EbiznesPage } from './app.po';

describe('ebiznes App', function() {
  let page: EbiznesPage;

  beforeEach(() => {
    page = new EbiznesPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
