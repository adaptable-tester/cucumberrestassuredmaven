# Run all tests
mvn test

# Run with a specific profile (e.g., 'dev')
mvn test -Pdev

# Run a specific TestNG suite
mvn test -DsuiteXmlFile=src/test/resources/testng.xml


Right-click testng.xml → Run.

Or use the Maven lifecycle in your IDE.

Chasing Implementation Detail Before Writing a Single Test Case
Developers discuss design and implementation among themselves. By the time a tester is involved, decisions have been made, pipelines are partially or fully built, and the tester is reverse-engineering intent from output. There is no formal handover. Test cases cannot be written with confidence when the transformation logic, edge cases, and expected outputs are unknown — so testers either delay, guess, or write shallow cases that give false assurance.

2. No Test Data Ready When Testing Begins
There is no agreed process for ensuring test data exists before a story enters testing. Testers have to source, create, or negotiate access to data themselves — often mid-sprint. SharePoint test folders don't exist, production access is limited or absent, and the snapshot strategy (happy path, empty response, missing fields, large volume) exists on paper but isn't systematically seeded. Every test cycle starts with a data scramble.

3. Blind to What's Actually in Each Environment
Testers don't know what version of a pipeline is deployed where, when it was last promoted, or whether the environment they're testing against reflects the code they're supposed to be testing. There's no deployment log, no notification, and no visibility of environment state. A failure could be a code bug, a config issue, or a deployment that hasn't happened yet — and the tester has no way to tell without chasing someone.

4. Writing Test Cases Against a Black Box
Without design documentation or a structured handover, testers are expected to write detailed test cases for systems they've never seen explained. This isn't testing — it's documentation archaeology. The time spent trying to understand what a pipeline is supposed to do is time not spent designing meaningful tests. The result is test cases that cover the surface, not the substance.

5. Absorbing Work That Isn't Theirs
Data mapping and data modelling work is landing on QAs by default — not because it's appropriate, but because nobody else is picking it up and it's blocking progress. This is a significant drain. Every hour spent on data mapping is an hour not spent on test strategy, automation, or critical quality analysis. It also creates a blurred accountability — the QA is now part-author of something they're also supposed to be testing independently.

6. ADO Boards That Don't Help
Stories don't have meaningful acceptance criteria. There's no layer tagging to indicate which part of the architecture a ticket touches. The board doesn't reflect actual project state. A tester trying to understand scope, plan regression coverage, or report quality status against a sprint goal is working without the basic tooling that should make that possible.

7. No Clarity on What "Done" Looks Like
Without written sprint goals, without INVEST-compliant stories, and without acceptance criteria tied to business intent, testers cannot definitively say whether something passes or fails. Sign-off becomes subjective. Conversations get circular. And when something goes wrong in production, there's no documented agreement to point back to.

8. Production Issues That Arrive Without Context
When something breaks in production and lands with QA, it arrives without a history of what was changed, when, or by whom. There's no incident log to reference, no deployment record to check, and no pattern to draw on. The tester starts from zero every time — which means the same vulnerabilities surface repeatedly and the same diagnostic effort is repeated from scratch.

Net Effect
Testers on this programme are spending a disproportionate amount of their time on information gathering, data wrangling, and scope clarification — all of which should be resolved before testing begins. The actual quality engineering work — test architecture, risk-based coverage, automation design, critical analysis — is being compressed into whatever time remains. That's the core problem, and it compounds with every sprint.
