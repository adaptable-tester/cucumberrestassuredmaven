# Run all tests
mvn test

# Run with a specific profile (e.g., 'dev')
mvn test -Pdev

# Run a specific TestNG suite
mvn test -DsuiteXmlFile=src/test/resources/testng.xml


Right-click testng.xml → Run.

Or use the Maven lifecycle in your IDE.
 

🏗️ Environment & Infrastructure Reality
These people know the actual state of environments, not the assumed state.
Ask them:

What are the environments (dev / test / UAT / prod) and how are they provisioned — are they like-for-like or are there differences in scale, config, or linked services?

Are any environments shared across projects? (This causes test interference you'd never trace otherwise)

What's the Azure Synapse workspace config per environment — same linked services? Same integration runtimes?

Are ADLS Gen2 container structures identical across environments or do they diverge?

What Databricks / Spark cluster configs are used per env — same runtime versions, node sizes, autoscaling settings?

Why it matters to you: A pipeline that passes in test can silently fail in prod because the linked service points somewhere different, the cluster is a different runtime, or a storage path doesn't exist yet. You need to know this before you sign off.

🚀 Deployment & Release Process
Data Ops own the path to production. You need a map of it.
Ask them:

How does code move from dev → test → UAT → prod? Is it ARM templates, Bicep, manual, CI/CD pipeline (Azure DevOps release pipeline)?

Who triggers deployments and is there any approval gate?

Is there a deployment log anywhere — even informal? What changed, when, and by whom?

Are pipeline parameters or config values different per environment — and where are those managed (Key Vault, parameter files, config tables)?

How are Synapse pipeline JSON definitions versioned and promoted — is there a Git integration in place?

Why it matters to you: Without this, you cannot tell whether a test failure is a code bug or a deployment/config issue. These are completely different problems requiring completely different responses. Knowing the deployment mechanism also tells you where test sign-off could be inserted as a gate.

🔥 Production Issues & Incident History
This is gold. Most testers never think to ask for it.
Ask them:

Have there been any pipeline failures or data quality issues in production in the last 3–6 months? What caused them?

Are there any known fragile pipelines or recurring failure patterns?

What does a typical production incident look like — how is it detected (monitoring alert, business complaint, data team spotting it)?

What's the escalation path when something breaks in prod — who gets called, in what order?

Are hotfixes applied directly to prod or do they go through the same deployment process?

Is there any monitoring / alerting in place (Azure Monitor, Data Factory alerts, custom logging)?

Why it matters to you: Every production incident that ever happened is a test case you haven't written yet. Recurring failures tell you exactly where to focus your regression effort. The detection mechanism tells you whether you need to add monitoring as part of your acceptance criteria — not just functional correctness.
