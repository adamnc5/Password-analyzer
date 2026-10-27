(function () {
  'use strict';
  const policy = window.PasswordPolicy;
  const get = (id) => document.getElementById(id);
  if (!policy) return;

  const form = get('analyzer-form');
  const password = get('password');
  const username = get('username');
  const examples = {
    short: 'hello123',
    common: 'PASSWORDPASSWORD',
    passphrase: 'an evening walk in Prague'
  };

  function updateMeter() {
    const length = Array.from(password.value).length;
    get('character-count').textContent = `${length} ${length === 1 ? 'character' : 'characters'}`;
    get('meter').setAttribute('aria-valuenow', String(Math.min(length, policy.MIN_LENGTH)));
    get('meter-fill').style.width = `${Math.min(100, length / policy.MIN_LENGTH * 100)}%`;
  }

  function setCheck(id, accepted) {
    const row = get(id);
    row.classList.remove('good', 'bad');
    if (accepted === null) {
      row.querySelector('.check-symbol').textContent = '—';
    } else {
      row.classList.add(accepted ? 'good' : 'bad');
      row.querySelector('.check-symbol').textContent = accepted ? '✓' : '×';
    }
  }

  function resetResult() {
    get('result-hero').className = 'result-banner';
    get('result-icon').textContent = '◇';
    get('result-title').textContent = 'Ready when you are';
    get('result-description').textContent = 'Type a fictional password to see which checks pass.';
    get('results-tag').textContent = 'READY';
    get('results-tag').className = 'report-badge';
    get('checks-count').textContent = 'Waiting for input';
    for (const id of ['rule-length', 'rule-blocklist', 'rule-printable']) setCheck(id, null);
    get('advice').hidden = true;
    get('advice-list').replaceChildren();
  }

  function analyze() {
    if (password.value === '') {
      resetResult();
      return;
    }
    const result = policy.analyze(password.value, username.value);
    get('result-hero').className = `result-banner ${result.accepted ? 'accepted' : 'rejected'}`;
    get('result-icon').textContent = result.accepted ? '✓' : '×';
    get('result-title').textContent = result.accepted ? 'Policy checks passed' : 'Review this example';
    get('result-description').textContent = result.accepted
      ? 'This example passes the demo rules. That is not a guarantee of real-world security.'
      : result.errors.join(' ');
    get('results-tag').textContent = result.accepted ? 'PASSED' : 'REVIEW';
    get('results-tag').className = `report-badge ${result.accepted ? 'passed' : 'failed'}`;
    setCheck('rule-length', result.checks.length);
    setCheck('rule-blocklist', result.checks.blocklist);
    setCheck('rule-printable', result.checks.printable);
    get('checks-count').textContent = `${Object.values(result.checks).filter((c) => c === true).length} of 3 passed`;
    const list = get('advice-list');
    list.replaceChildren();
    for (const message of result.advice) {
      const entry = document.createElement('li');
      entry.textContent = message;
      list.appendChild(entry);
    }
    get('advice').hidden = result.advice.length === 0;
  }

  function handleInput() {
    updateMeter();
    analyze();
  }

  password.addEventListener('input', handleInput);
  username.addEventListener('input', analyze);
  form.addEventListener('submit', (event) => {
    event.preventDefault();
    handleInput();
  });
  get('clear-button').addEventListener('click', () => {
    password.value = '';
    username.value = '';
    handleInput();
    password.focus();
  });
  get('toggle-visibility').addEventListener('click', function () {
    const visible = password.type === 'text';
    password.type = visible ? 'password' : 'text';
    this.textContent = visible ? 'Show' : 'Hide';
    this.setAttribute('aria-label', visible ? 'Show example password' : 'Hide example password');
    this.setAttribute('aria-pressed', String(!visible));
    password.focus();
  });
  for (const button of document.querySelectorAll('[data-example]')) {
    button.addEventListener('click', () => {
      password.value = examples[button.dataset.example];
      username.value = '';
      handleInput();
      password.focus();
    });
  }

  handleInput();
})();
